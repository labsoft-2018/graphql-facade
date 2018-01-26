(ns graphql-facade.graphql.resolvers.mutations.user
  (:require [common-labsoft.protocols.http-client :as protocols.http-client]
            [graphql-facade.utils :as utils]
            [graphql-facade.diplomat.http :as diplomat.http]
            [io.pedestal.log :as log]))



(defn user-response->mutation-payload
  [response]
  {:user  (update-in response [:type] utils/unnamespaced)
   :token (-> response :token :jwt)})

(defn merchant-sign-in-with-email
  [{:keys [http]} {:keys [email password]} value]
  (-> (diplomat.http/get-token-with-email http :user.type/merchant email password)
      utils/unnamespaced-map
      user-response->mutation-payload))

(defn carrier-sign-in-with-email
  [{:keys [http]} {:keys [email password]} value]
  (-> (diplomat.http/get-token-with-email http :user.type/carrier email password)
      utils/unnamespaced-map
      user-response->mutation-payload))

(defn customer-sign-in-with-email
  [{:keys [http]} {:keys [email password]} value]
  (-> (diplomat.http/get-token-with-email http :user.type/customer email password)
      utils/unnamespaced-map
      user-response->mutation-payload))

(defn customer-sign-in-with-facebook
  [{:keys [http]} {:keys [facebookToken]} value]
  (println facebookToken)
  (-> (diplomat.http/get-token-with-facebook http facebookToken)
      utils/unnamespaced-map
      user-response->mutation-payload))

(defn sign-up-user
  [ctx args value]
  {:id "123"})