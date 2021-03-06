(ns ftgeo.client
  (:require [om.next :as om]
            [fulcro.client.core :as uc]
            [fulcro.client.data-fetch :as f]
            [ftgeo.api.mutations :as m]
            [ftgeo.ui.html5-routing :as routing]
            [fulcro.client.mutations :as built-in]
            [ftgeo.ui.root :as root]
            translations.es
            [ftgeo.ui.user :as user]
            [fulcro.client.logging :as log]
            [fulcro.server-render :as ssr]))

(defonce app
  (atom (uc/new-fulcro-client
          :initial-state (when-let [v (ssr/get-SSR-initial-state)] ; the client starts with the server-generated db, if available
                           (atom v))                        ; putting the state in an atom tells Om it is already normalized
          :started-callback (fn [{:keys [reconciler] :as app}]
                              (let [state (om/app-state reconciler)
                                    root  (om/app-root reconciler)
                                    {:keys [ui/locale ui/ready?]} @state]
                                (if ready?                  ; The only way ready is true, is if we're coming from a server-side render
                                  (routing/start-routing root)
                                  (f/load app :current-user user/User {:post-mutation        `m/login-complete
                                                                       :post-mutation-params {:app-root (om/app-root reconciler)}})))))))
