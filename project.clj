(defproject graphql-facade "0.1.1-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "All Rights Reserved"}
  :deploy-repositories [["clojars" {:url      "https://clojars.org/repo"
                                    :username :env/clojars_username
                                    :password :env/clojars_password}]]
  :plugins [[lein-midje "3.2.1"]]
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [labsoft-2018/common-labsoft "0.8.0-SNAPSHOT"]
                 [com.walmartlabs/lacinia "0.21.0"]
                 [com.walmartlabs/lacinia-pedestal "0.3.0"]]
  :resource-paths ["resources"]
  :min-lein-version "2.0.0"
  :profiles {:dev {:aliases {"run-dev" ["trampoline" "run" "-m" "graphql-facade.service/start!"]}
                   :dependencies [[io.pedestal/pedestal.service-tools "0.5.3"]]}
             :uberjar {:aot [graphql-facade.service]}}
  :main ^{:skip-aot true} graphql-facade.service)
