(ns ftgeo.ui.server-side-rendering-spec
  (:require
    [ftgeo.ui.html5-routing :as r]
    [fulcro-spec.core :refer [specification provided behavior assertions when-mocking]]
    [fulcro.client.util :as util]
    [fulcro.client.core :as fc]
    [ftgeo.ui.main :as main]
    [ftgeo.ui.root :as root]
    [fulcro.server-render :as ssr]))

#?(:clj
   (specification "Server side rendering of index"
     (let [state (ssr/build-initial-state (fc/get-initial-state root/Root nil) root/Root)]
       (assertions
         "Initial state for SSR starts with the default state for the ui"
         (contains? state :logged-in?) => true
         (contains? state :ui/ready?) => true))))
