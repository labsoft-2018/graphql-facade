(ns graphql-facade.graphql.resolvers.mutations.user
  (:require [common-labsoft.protocols.http-client :as protocols.http-client]
            [graphql-facade.utils :as utils]
            [io.pedestal.log :as log]))

(defn token-request!
  [http user-type cred-type email password]
  (protocols.http-client/authd-req! http {:method :post
                                          :url    :auth/token
                                          :body   {:auth/user-type (utils/namespaced "user.type" user-type)
                                                   :auth/cred-type (utils/namespaced "credential.type" cred-type)
                                                   :auth/email     email
                                                   :auth/password  password}}))
(defn token-response->mutation-payload
  [response]
  {:user  (update-in response [:type] utils/unnamespaced)
   :token (-> response :token :jwt)})

(defn sign-in-user
  [{:keys [http]} {{:keys [userType credType email password]} :input} value]
  (-> (token-request! http userType credType email password)
      utils/unnamespaced-map
      token-response->mutation-payload))

(defn sign-up-user
  [ctx args value]
  {:id "123"})