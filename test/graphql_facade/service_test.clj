(ns graphql-facade.service-test
  (:require [midje.sweet :refer :all]
            [graphql-facade.aux.test-helpers :as th]))

(def service (th/test-service))

(fact "Http Test"
  (th/request! service :get "/") => {:res "Hello, World!"})
