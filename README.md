# META4ICS - Metric Analyser for Industrial Control Systems
### Version 0.53.1

## Usage
$> java -jar meta4ics.jar examples/simple/example1.json
== META4ICS v0.53.1 ==
== Started at 2019-06-10 19:52:03.741 ==

=> Loading problem specification...  done in 222 ms (0 seconds).
----------------------------------
Problem source: s
Problem target: c1
----------------------------------
=> Performing Tseitin transformation...  done in 7 ms (0 seconds).
|+| Solvers: [MaxSAT]

==================================
=> BEST solution found by MAX-SAT-SOLVER for:
Source: s
Target: c1
=== Security Metric ===
CUT cost: 4.0
Total critical nodes: 2
[+] Critical nodes: (a,2); (c,2);
[*] Metric computation time: 59 ms (0 seconds).
==================================
Solution saved in: ./view/sol.json
== META4ICS ended at 2019-06-10 19:52:04.058 ==


## View the solution
$> ./web-viewer.py
Running in Python 2...
('Started HTTP server on port ', 8000)

Go to the browser and insert the viewer URL:
http://localhost:8000/viz.html

You should see the graph and the critical nodes.
