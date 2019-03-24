(ns learn.core
  (:require [learn.mapregex :as rt])
  (:gen-class)
  )

; other import solutions:
;(require '[learn.mapregex :as rt])
;(use '[learn.mapregex])

; min size is 101 otherwise the partitioning won't work properly in some cases
(def ^:const size 50000)
(def ^:const line-length 400)
(def ^:const pattern #"(?i)199")

; fully random data
(def init-data (repeatedly size (fn [] (repeatedly line-length #(rand-int 10)))))

; size times a randomly generated line
;(def init-data (repeat size (repeatedly line-length #(rand-int 100))))

; simlpe incremental test data
;(def init-data (repeat size (range 1100)))

(defn all-bench [num flist]
  (map (fn [name undertest]
         (println "** " name)
         ;(quick-bench (showPrimesTo v num))
         (time (undertest num))
         ) flist)
  )

(defn all-bench-ugly [pattern init-data]
  (time ( println "rt/t_tesser \n" ( rt/t_tesser pattern init-data)))
  (time ( println "rt/t_rmap \n" ( rt/t_rmap pattern init-data)))
  (time ( println "rt/t_pmap_double \n" ( rt/t_pmap_double pattern init-data)))
  (time ( println "rt/t_pmap_part \n" ( rt/t_pmap_part pattern init-data)))
  (time ( println "rt/t_pmap_single \n" ( rt/t_pmap_single pattern init-data)))
  (time ( println "rt/t_simple \n" ( rt/t_simple pattern init-data)))
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
  (nth init-data (- size 1))

  (all-bench-ugly pattern init-data)
  (System/exit 0)
  )

