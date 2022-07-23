(ns app 
  (:require [hyperfiddle.photon :as p]
            [hyperfiddle.photon-dom :as dom]
            [hyperfiddle.photon-ui :as ui])
  #?(:cljs (:require-macros app)))

(defonce !x #?(:clj (atom true) :cljs nil)) ; server
(p/def x (p/server (p/watch !x)))

(p/defn App []
 (p/client
  (ui/button {::ui/click-event (p/fn [e] (p/server (swap! !x not)))}
   (dom/text "toggle client/server"))
  (dom/div
   (dom/text "(type 1) is: " (if (p/server x) 
                              (p/client (pr-str (type 1)))
                              (p/server (pr-str (type 1))))))))