(ns graphql-facade.routes
  (:require [common-labsoft.pedestal.interceptors.auth :as int-auth]
            [common-labsoft.pedestal.interceptors.error :as int-err]
            [common-labsoft.pedestal.interceptors.schema :as int-schema]
            [io.pedestal.http.route.definition :refer [defroutes]]
            [io.pedestal.http :as http]
            [io.pedestal.http.body-params :as body-params]
            [graphql-facade.graphql.schema :as schema]
            [graphql-facade.adapters.context :as a-ctx]
            [graphql-facade.graphql.graphiql :as graphiql]
            [com.walmartlabs.lacinia :as lacinia]))

(defn graphiql-handler
  [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (graphiql/render-html)})

(defn make-graphql-handler
  [schema context-adapter]
  (fn [request]
    (let [query (get-in request [:json-params :query])
          variables (get-in request [:json-params :variables])
          context (context-adapter request)]
      {:status 200
       :body   (lacinia/execute schema query variables context)})))

(defroutes routes
           [[["/" ^:interceptors [int-err/catch!
                                  (body-params/body-params)
                                  http/json-body
                                  int-auth/auth
                                  int-schema/coerce-output]
              ["/graphql" {:get [:graphiql graphiql-handler]}]
<<<<<<< Updated upstream
              ["/graphql" {:post [:graphql-query (make-graphql-handler schema/facade-schema a-ctx/req->context)]}]]]])
=======
              ["/graphql" {:post [:graphql-query (make-graphql-handler (schema/load-schema) a-ctx/req->context)]}]]]])
>>>>>>> Stashed changes
