# META4ICS - Metric Analyser for Industrial Control Systems
### Version 0.53.1


## Requirements
* Java 8
* Python 2/3
* Optional: Python 3, PuLP, and Gurobi to enable second MaxSAT solver

## Usage

1. ```java -jar meta4ics.jar inputFile.json [-c configFile]```  
This command executes META4ICS with an input JSON file that describes the network under analysis. 
The method used by META4ICS to identify critical nodes is fully described in our paper: 
[Identifying Security-Critical Cyber-Physical Components in Industrial Control Systems](https://arxiv.org/abs/1905.04796)

2. ```./web-viewer.py```  
This command launches the webviewer (Python-based HTTP server) that displays the AND/OR graph as well as its critical nodes. 
By default, the webviewer reads the file *view/sol.json* and displays it at [http://localhost:8000/viz.html](http://localhost:8000/viz.html)



## Execution examples

### Example 1: AND/OR graphs with weighted nodes
```
$> java -jar meta4ics.jar examples/simple/example1.json
```
```
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
```

##### Run the webviewer:
```
$> ./web-viewer.py
```
```
Running in Python 2...
('Started HTTP server on port ', 8000)
```
In the browser, go to [http://localhost:8000/viz.html](http://localhost:8000/viz.html)  
You should see the following AND/OR graph along with its critical nodes (*a* and *c*):

![Screenshot - simple example](https://github.com/mbarrere/meta4ics/blob/master/screenshots/example1.png)

---

### Example 2: AND/OR hypergraph (multiple overlapping security measures)
```
$> java -jar meta4ics.jar examples/hypergraphs/ics-expanded.json 
```
```
== META4ICS v0.53.1 ==
== Started at 2019-06-11 15:54:33.639 ==

=> Loading problem specification...  done in 242 ms (0 seconds).
----------------------------------
Problem source: _s_
Problem target: c1
Available measures: 
	Measure [id=F1, cost=1, desc=Wire Fenced area]
	Measure [id=F2, cost=2, desc=Underground Facility]
	Measure [id=B1, cost=2, desc=Locked Building]
	Measure [id=B2, cost=8, desc=Secure Locked Building]
	Measure [id=A1, cost=12, desc=Door Alarm]
	Measure [id=A2, cost=18, desc=Alarm on Telemetry]
	Measure [id=A3, cost=3, desc=Patrol Unit]
	Measure [id=P1, cost=2, desc=Locked Box]
	Measure [id=P2, cost=8, desc=Cable Protection]
----------------------------------
=> Performing Tseitin transformation...  done in 10 ms (0 seconds).
|+| Solvers: [MaxSAT]

==================================
=> BEST solution found by MAX-SAT-SOLVER for:
Source: _s_
Target: c1
=== Security Metric ===
CUT cost: 15.0
Total critical nodes: 2
Involved security measures: 5
[+] Critical nodes: (a1,0); (s2,0); 
[+] Security measure instances: 
	(F1-1,1) -> [c1,s1,s2,a2,a7,a8,a10];
	(B2-1,8) -> [c1,s1,s2,a2,a7,a8,a10];
	(F1-2,1) -> [s5,s4,a3,a9,a1];
	(B1-1,2) -> [s5,s4,a3,a9,a1];
	(A3-1,3) -> [s5,s4,s6,a3,a9,a1];
[+] Full solution: (F1-1,1); (B2-1,8); (a1,0); (F1-2,1); (B1-1,2); (A3-1,3); (s2,0); 
[+] Critical nodes display: (a1,6); (s2,9); 
[*] Metric computation time: 69 ms (0 seconds).
==================================
Solution saved in: ./view/sol.json
== META4ICS ended at 2019-06-11 15:54:34.003 ==
```

##### Run the webviewer:

If the webviewer is active, just go to [http://localhost:8000/viz.html](http://localhost:8000/viz.html), otherwise launch the webviewer (as shown in the first example) and follow the link. 
You should see the following hypergraph and critical nodes:  

![Screenshot - hypergraph example](https://github.com/mbarrere/meta4ics/blob/master/screenshots/ics-expanded.png)

---


## Configuration parameters `meta4ics.conf`
The configuration parameters are stored in the file `meta4ics.conf`. 
The tool also accepts a different configuration file as argument [-c configFile] to override the configuration in *meta4ics.conf*. If the file `meta4ics.conf` is not present, META4ICS uses the default configuration values (see below). 

### Solvers
* ```solvers.sat4j = true``` Enables/disables default MaxSAT solver (*default=true*)
* ```solvers.optim = false``` Enables/disables second Gurobi-based MaxSAT solver (*default=false*)

### Python environment
* ```python.path = /usr/local/bin/python3``` Specifies the path to the Python 3 binary (only used with the second [optional] Gurobi-based solver). 
* ```python.solver.path = python/optim.py``` Specifies the path to the Gurobi-based solver. 

### Output flags
* ```output.sol = true``` Indicates META4ICS to output the JSON solution with the critical nodes. 
* ```output.wcnf = false``` Enables/disables the specification of the problem in WCNF (DIMACS-like) format (*default=false*). The WCNF file can be used to experiment with other MaxSAT solvers. 
* ```output.txt = false``` Enables/disables the specification of the problem in a simple list-based representation file (*default=false*). 


### Output folders
* ```folders.output = output``` Specifies the default output folder for *.wcnf* and *.txt* files.
* ```folders.view = view``` Specifies the default view folder where the solution (*sol.json*) is stored. 

### Debug
* ```tool.debug = false``` Enables light debugging. 
* ```tool.fulldebug = false``` Enables full (heavy) debugging. 

