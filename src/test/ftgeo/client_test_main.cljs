(ns ftgeo.client-test-main
  (:require ftgeo.tests-to-run
            [fulcro-spec.selectors :as sel]
            [fulcro-spec.suite :as suite]))

(enable-console-print!)

(suite/def-test-suite client-tests {:ns-regex #"ftgeo\..*-spec"}
  {:default   #{::sel/none :focused}
   :available #{:focused}})

