(ns graphql-facade.graphql.resolvers.queries.merchant)

(defn user
  [ctx args value]
  {:id "123"})

(defn orders
  [ctx args value]
  [{:id "123"}])