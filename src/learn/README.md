
***Some test results:***

4*8 CPU core (64 core with HT):

Simple perf test of different map implementations

*settings:*
- (def ^:const size 50000)
- (def ^:const line-length 400)
- (def ^:const pattern #"(?i)199")

*results:*
- rt/t_tesser
  - 19692
  - "Elapsed time: 1341.326938 msecs"
- rt/t_rmap
  - 19692
  - "Elapsed time: 4616.878361 msecs"
- rt/t_pmap_double
  - 19692
  - "Elapsed time: 770.92377 msecs"
- rt/t_pmap_part
  - 19692
  - "Elapsed time: 5052.190332 msecs"
- rt/t_pmap_single
  - 19692
  - "Elapsed time: 625.481177 msecs"
- rt/t_simple
  - 19692
  - "Elapsed time: 4938.270507 msecs"
- rt/t_future
  - 19692
  - "Elapsed time: 4954.324755 msecs"
  
  
*I7 4 core (8HT) results*
- rt/t_tesser
  - 19798
  - "Elapsed time: 483.5542 msecs"
- rt/t_rmap
  - 19798
  - "Elapsed time: 1419.7344 msecs"
- rt/t_pmap_double
  - 19798
  - "Elapsed time: 456.5823 msecs"
- rt/t_pmap_part
  - 19798
  - "Elapsed time: 1569.3695 msecs"
- rt/t_pmap_single
  - 19798
  - "Elapsed time: 433.8762 msecs"
- rt/t_simple
  - 19798
  - "Elapsed time: 1287.8339 msecs"
- rt/t_future
  - 19798
  - "Elapsed time: 1355.0535 msecs"
