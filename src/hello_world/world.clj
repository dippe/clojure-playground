(ns hello-world.world
  (:require [clojure.core.reducers :as r]
            [squeezer.core :as sc])
  (:import (java.io BufferedReader)))


(require '[iota                  :as iota]
         '[clojure.core.reducers :as r]
         '[squeezer.core :as sc]
         '[clojure.string        :as str])



; REPL: (use 'hello-world.world :reload)


(str "Hello" " " "valami" " " "world")

(defn -main [& args]
  (slurp "LICENSE")

  (println "valamicsoda")

  )


;---------------------------------------------------------------
(with-open [reader (clojure.java.io/reader "./LICENSE")]
  (reduce (
            (fn [acc curr] (if-let [line (re-find #"(?i)this" curr)] (conj acc line) acc))
            ) [] (line-seq reader)))


(defn ifLineMatches[acc curr] (if-let [line (re-find #"(?i)this" curr)] (conj acc line) acc))

; muxik ---------------------------------------------------------------
(with-open [in (clojure.java.io/reader "./enwiki-latest-abstract1.xml.gz.out.txt")
            out (clojure.java.io/writer "test-out.txt")
            ] (binding [*out* out]
                (->> in
                     (line-seq)
                     ;(map #(if-let [line (re-find #"(?i)this" %1)] line nil))
                     (reduce
                       (fn [acc curr] (if-let [line (re-find #"(?i)^<url" curr)] (conj acc curr) acc))
                                [])
                     ;(map #(str %1 "\n"))
                     (map println)
                     (dorun)
                     ) ) )


; muxik2
(with-open [in (clojure.java.io/reader "./enwiki-latest-abstract1.xml.gz.out.txt")
            out (clojure.java.io/writer "test-out.txt")
            ] (binding [*out* out]
                (->> in
                     (line-seq)
                     ;(map #(if-let [line (re-find #"(?i)this" %1)] line nil))
                     (r/reduce
                       (fn [acc curr] (if-let [line (re-find #"(?i)^<url" curr)] (conj acc curr) acc))
                       [])
                     ;(map #(str %1 "\n"))
                     (map println)
                     (dorun)
                     ) ) )

; muxik ------------------------------------------------------------------------
(require '[iota                  :as iota]
         '[clojure.core.reducers :as r]
         '[squeezer.core :as sc]
         '[clojure.string        :as str])


(with-open [in (clojure.java.io/reader "./enwiki-latest-abstract1.xml.gz.out.txt")
            ] (->> in
                     (line-seq)
                     ;(map #(if-let [line (re-find #"(?i)this" %1)] line nil))
                     (r/reduce
                       (fn [acc curr] (if-let [line (re-find #"(?i)^<url" curr)] (conj acc curr) acc))
                       [])
                     ;(map #(str %1 "\n"))
                     ;(map println)
                      (sc/spit-compr "test-out.txt.gz")
                     ) )


; Muxik ------------------------------------------------------------------------
(import java.util.zip)
(require '[iota                  :as iota]
         '[clojure.core.reducers :as r]
         '[squeezer.core :as sc]
         '[clojure.string        :as str])


(with-open [in (java.util.zip.GZIPInputStream.
                 (clojure.java.io/input-stream
                   "fox.txt.gz"))]
  (println "result:" (slurp in)))

( defn gz-in2-simplified [] (with-open [in (->
                                  "./enwiki-latest-abstract1.xml.gz.out.txt.gz"
                                  clojure.java.io/input-stream
                                  GZIPInputStream.
                                  clojure.java.io/reader )
                            ] (->> in
                                   (line-seq)
                                   ;(map #(if-let [line (re-find #"(?i)this" %1)] line nil))
                                   (r/reduce
                                     (fn [acc curr] (if-let [line (re-find #"(?i)^<url" curr)] (conj acc curr) acc))
                                     [])
                                   (sc/spit-compr "test-out.txt.gz")))
  )

; Muxik ------------------------------------------------------------------------

(import java.util.zip)
(with-open [in (java.util.zip.GZIPInputStream.
                 (clojure.java.io/input-stream
                   "fox.txt.gz"))]
  (println "result:" (slurp in)))

( defn gz-in [] (with-open [in ( GZIPInputStream. ( clojure.java.io/input-stream "./enwiki-latest-abstract1.xml.gz.out.txt.gz") )
            ] (->> in
                   java.io.InputStreamReader.
                   java.io.BufferedReader.
                                                 ;(slurp)
                                                 (line-seq)
                                                 ;(map #(if-let [line (re-find #"(?i)this" %1)] line nil))
                                                 (reduce
                     (fn [acc curr] (if-let [line (re-find #"(?i)^<url" curr)] (conj acc curr) acc))
                     [])
                                                 ;(map #(str %1 "\n"))
                                                 ;(map println)
                                                 (sc/spit-compr "test-out.txt.gz")))
)

; Muxik elvileg, de tul nagy a fajl ------------------------------------------------------------------------
; slurp: causes java HEAP error!!
( defn gz-in3-simplified-slurp [] (with-open [in (->
                                                   "./enwiki-latest-abstract1.xml.gz.out.txt.gz"
                                                   clojure.java.io/input-stream
                                                   GZIPInputStream.
                                                   slurp )
                                              ] (->> in
                                                     (line-seq)
                                                     ;(map #(if-let [line (re-find #"(?i)this" %1)] line nil))
                                                     (r/reduce
                                                       (fn [acc curr] (if-let [line (re-find #"(?i)^<url" curr)] (conj acc curr) acc))
                                                       [])
                                                     (sc/spit-compr "test-out.txt.gz")))
  )


; ?? ------------------------------------------------------------------------



(with-open [in (clojure.java.io/reader "./enwiki-latest-abstract1.xml.gz.out.txt")
            out (clojure.java.io/writer "test-out.txt")
            ] (binding [*out* out]
                (->> in
                     (line-seq)
                     ;(map #(if-let [line (re-find #"(?i)this" %1)] line nil))
                     (r/reduce
                       (fn [acc curr] (if-let [line (re-find #"(?i)^<url" curr)] (conj acc curr) acc))
                       [])
                     (r/foldcat)
                     ;(map #(str %1 "\n"))
                     ;(r/map println)
                     ;(dorun)
                     ) ) )
