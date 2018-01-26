(ns graphql-facade.diplomat.http
  (:require [common-labsoft.protocols.http-client :as protocols.http-client]))

(defn get-token-with-email
  [http user-type email password]
  (protocols.http-client/authd-req! http {:method :post
                                          :url    :auth/token
                                          :body   {:auth/user-type user-type
                                                   :auth/cred-type :credential.type/password
                                                   :auth/email     email
                                                   :auth/password  password}}))
(defn get-token-with-facebook
  [http fb-token]
  (protocols.http-client/authd-req! http {:method :post
                                          :url    :auth/token
                                          :body   {:auth/user-type :user.type/customer
                                                   :auth/cred-type :credential.type/facebook
                                                   :auth/fb-token  fb-token}}))