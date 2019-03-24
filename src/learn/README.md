
***Some test results:***

4*8 CPU core (64 core with HT):

Simple perf test of different map implementations

*settings:*
- (def ^:const size 50000)
- (def ^:const line-length 400)
- (def ^:const pattern #"(?i)199")

*results:*
- rt/t_tesser
  -  found regex: 16543
  - "Elapsed time: 20866.753639 msecs"
- rt/t_rmap
  -  found regex: 16543
  - "Elapsed time: 4415.491457 msecs"
- rt/t_pmap_double
  -  found regex: 16543
  - "Elapsed time: 1392.398246 msecs"
- rt/t_pmap_part
  -  found regex: 16543
  - "Elapsed time: 3641.134427 msecs"
- rt/t_pmap_single
  -  found regex: 16543
  - "Elapsed time: 549.194523 msecs"
- rt/t_simple
  -  found regex: 16543
  - "Elapsed time: 3485.683274 msecs"
  
  
*I7 4 core results*
rt/t_tesser
 16384
"Elapsed time: 4820.7045 msecs"
rt/t_rmap
 16384
"Elapsed time: 1307.1558 msecs"
rt/t_pmap_double
 16384
"Elapsed time: 396.1423 msecs"
rt/t_pmap_part
 16384
"Elapsed time: 1144.9652 msecs"
rt/t_pmap_single
 16384
"Elapsed time: 324.94 msecs"
rt/t_simple
 16384
"Elapsed time: 1111.4171 msecs"
