(ns learn.core
  (:require [learn.mapregex :as rt])
  (:gen-class)
  )

; other import solutions:
;(require '[learn.mapregex :as rt])
;(use '[learn.mapregex])

(def count 100000)

(def init-data (repeat count (range 1100)))

(defn all-bench [num flist]
  (map (fn [name undertest]
         (println "** " name)
         ;(quick-bench (showPrimesTo v num))
         (time (undertest num))
         ) flist)
  )

(defn all-bench-ugly [init-data]
  (time ( println "rt/t_tesser \n" ( rt/t_tesser init-data)))
  (time ( println "rt/t_rmap \n" ( rt/t_rmap init-data)))
  (time ( println "rt/t_pmap_double \n" ( rt/t_pmap_double init-data)))
  (time ( println "rt/t_pmap_part \n" ( rt/t_pmap_part init-data)))
  (time ( println "rt/t_pmap_single \n" ( rt/t_pmap_single init-data)))
  (time ( println "rt/t_simple \n" ( rt/t_simple init-data)))
)

(defn -main []
  (println "Simple perf test of different map implementations")
  ;(all-bench 100 {
  ;                "t_tesser"      rt/t_tesser
  ;                "t_rmap"        rt/t_rmap
  ;                "t_pmap_double" rt/t_pmap_double
  ;                "t_pmap_part"   rt/t_pmap_part
  ;                "t_pmap_single" rt/t_pmap_single
  ;                "t_simple"      rt/t_simple
  ;                })

  ;pre-load init-data
  (nth init-data (- count 1))

  (all-bench-ugly init-data)
  (System/exit 0)
  )

