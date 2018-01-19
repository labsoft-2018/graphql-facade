(ns graphql-facade.graphql.graphiql
  (:require [clojure.java.io :as io]))



(defn render-html
  []
  (-> (io/resource "graphiql.html")
      slurp))