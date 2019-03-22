(ns learn.core
  (:require [learn.mapregex :as rt])
  (:gen-class)
  )

; other import solutions:
;(require '[learn.mapregex :as rt])
;(use '[learn.mapregex])

(defn all-bench [num flist]
  (map (fn [name undertest]
         (println "** " name)
         ;(quick-bench (showPrimesTo v num))
         (time (undertest num))
         ) flist)
  )

(defn all-bench-ugly [num]
  (time ( println "rt/t_tesser \n" ( rt/t_tesser num )))
  (time ( println "rt/t_rmap \n" ( rt/t_rmap num )))
  (time ( println "rt/t_pmap_double \n" ( rt/t_pmap_double num )))
  (time ( println "rt/t_pmap_part \n" ( rt/t_pmap_part num )))
  (time ( println "rt/t_pmap_single \n" ( rt/t_pmap_single num )))
  (time ( println "rt/t_simple \n" ( rt/t_simple num )))
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

  (all-bench-ugly 100000)
  (System/exit 0)
  )

