(ns app (:require [hyperfiddle.photon :as p]
                  [hyperfiddle.photon-dom :as dom]
                  [hyperfiddle.photon-ui :as ui])
  #?(:cljs (:require-macros app)))

(defonce !n #?(:clj (atom 0) :cljs nil)) ; server
(p/def n (p/server (p/watch !n)))

(p/defn App []
 (p/client
  (ui/button {::ui/click-event (p/fn [e] (p/server (swap! !n inc)))}
   (dom/text "click me " (p/server n)))
  (dom/p 
   (if (p/server (odd? n))
     (dom/text "client " (pr-str (type (p/server n))))
     (dom/text "server " (p/server (pr-str (type n))))))))
