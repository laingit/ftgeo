(ns ftgeo.client-main
  (:require [ftgeo.client :refer [app]]
            [fulcro.client.core :as core]
            translations.es
            [ftgeo.ui.root :as root]))

;; In dev mode, we mount from cljs/user.cljs
(reset! app (core/mount @app root/Root "app"))
