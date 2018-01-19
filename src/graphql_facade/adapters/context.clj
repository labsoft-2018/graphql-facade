(ns graphql-facade.adapters.context)

(defn req->context
  [{:keys [components] :as request}]
  (merge (select-keys components [:http])
         {:other 12}))
