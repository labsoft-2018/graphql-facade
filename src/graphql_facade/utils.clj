(ns graphql-facade.utils)

(defn unnamespaced [str-or-kw]
  (-> str-or-kw keyword name keyword))

(defn unnamespaced-map [map]
  (clojure.walk/postwalk #(if (keyword? %) (keyword (name %)) %) map))

(defn namespaced [ns str-or-kw]
  (keyword ns (name str-or-kw)))
