(ns learn.mapregex
  ;(:require [tesser.core :as tesser])
  ;(:require [clojure.core/reducers :as r])
  )

(require '[clojure.core.reducers :as r])
(require '[tesser.core :as t])
;(require '[tesser.core :as tesser])

;(use 'tesser.core :as tesser)

; normal impl
;"Elapsed time: 10400.248806 msecs"
(defn t_simple [pattern init-data] (->>
                       init-data
                       (map #(clojure.string/join %))
                       (map #(re-find pattern %))
                       (remove nil?)
                       (count)
                       )
  )

;"Elapsed time: 6040.449344 msecs"
(defn t_pmap_double [pattern init-data] (->>
                            init-data
                            (pmap #(clojure.string/join %))
                            (pmap #(re-find pattern %))
                            (remove nil?)
                            (count)
                            )
  )

;"Elapsed time: 5966.242608 msecs"
(defn t_pmap_single [pattern init-data] (->>
                            init-data
                            (pmap (fn [arr] (re-find pattern (clojure.string/join arr))))
                            (remove nil?)
                            (count)
                            )
  )

;"Elapsed time: 10466.158169 msecs"
(defn t_pmap_part [pattern init-data] (->>
                          init-data
                          (partition 100)
                          (pmap #(map (fn [subarr] (clojure.string/join subarr)) %))
                          (pmap #(map (fn [subarr] (re-find pattern subarr)) %))
                          (flatten)
                          (remove nil?)
                          (count)
                          )
  )


;"Elapsed time: 10326.207215 msecs"
(defn t_rmap [pattern init-data] (->>
                     init-data
                     (r/map #(clojure.string/join %))
                     (r/map #(re-find pattern %))
                     (r/foldcat)
                     (remove nil?)
                     (count)
                     )
  )
; tesser impl:
(defn t_tesser [pattern init-data] (->>
                       (t/map #(clojure.string/join %))
                       (t/map #(re-find pattern %))
                       (t/remove nil?)
                       (t/count)
                       (t/tesser (partition 100 init-data))

                       )
  )
