(ns graphql-facade.graphql.schema
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]
            [com.walmartlabs.lacinia.util :as util]
            [com.walmartlabs.lacinia.schema :as schema]
            [graphql-facade.graphql.resolvers.queries.viewer :as r-v]
            [graphql-facade.graphql.resolvers.queries.merchant :as r-mer]
            [graphql-facade.graphql.resolvers.queries.carrier :as r-ca]
            [graphql-facade.graphql.resolvers.queries.customer :as r-cus]
            [graphql-facade.graphql.resolvers.queries.order :as r-ord]
            [graphql-facade.graphql.resolvers.queries.user :as r-u]
            [graphql-facade.graphql.resolvers.queries.queries :as r-q]
            [graphql-facade.graphql.resolvers.mutations.user :as m-u]
            [graphql-facade.graphql.resolvers.mutations.merchant :as m-mer]
            [graphql-facade.graphql.resolvers.mutations.order :as m-ord]
            [graphql-facade.graphql.resolvers.mutations.carrier :as m-ca]
            [graphql-facade.graphql.resolvers.mutations.customer :as m-cus]))


(defn resolver-map
  []
  {:queries/viewer            r-q/viewer
   :viewer/all-merchants      r-v/all-merchants
   :viewer/order              r-v/order
   :viewer/user               r-v/user
   :user/merchant             r-u/merchant
   :user/customer             r-u/customer
   :user/carrier              r-u/carrier
   :customer/user             r-cus/user
   :carrier/user              r-ca/user
   :merchant/user             r-mer/user
   :merchant/orders           r-mer/orders
   :order/carrier             r-ord/carrier
   :order/customer            r-ord/customer
   :mutations/sign-in-user    m-u/sign-in-user
   :mutations/sign-up-user    m-u/sign-up-user
   :mutations/create-carrier  m-ca/create-carrier
   :mutations/create-customer m-cus/create-customer
   :mutations/create-merchant m-mer/create-merchant})

(defn load-schema
  []
  (-> (io/resource "schema.edn")
      slurp
      edn/read-string
      (util/attach-resolvers (resolver-map))
      schema/compile))