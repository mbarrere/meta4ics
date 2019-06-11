# META4ICS - Metric Analyser for Industrial Control Systems
### Version 0.53.1


## Requirements
* Java 8
* Python 2/3
* Optional: Python 3, PuLP, and Gurobi to enable second MaxSAT solver

## Usage

1. ```java -jar meta4ics.jar inputFile.json [-c configFile]```  
This command executes META4ICS with an input JSON file that describes the network under analysis. 
The method used by META4ICS to compute the critical nodes is fully described in our paper: 
[Identifying Security-Critical Cyber-Physical Components in Industrial Control Systems](https://arxiv.org/abs/1905.04796)

2. ```./web-viewer.py```  
This command launches the webviewer (Python-based HTTP server) that displays the AND/OR graph with the critical nodes. 
By default, it display is located at [http://localhost:8000/viz.html](http://localhost:8000/viz.html)

## Execution example

### 1. Metric computation
```
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
```

### 2. View the solution
```
$> ./web-viewer.py
Running in Python 2...
('Started HTTP server on port ', 8000)
```
Go to the browser and insert the viewer's URL: [http://localhost:8000/viz.html](http://localhost:8000/viz.html)

You should see the graph and the critical nodes:

![Screenshot - simple example](https://github.com/mbarrere/meta4ics/blob/master/screenshots/example1.png)


## Configuration parameters
The configuration parameters are stored in the file ```meta4ics.conf```. 
The tool also accepts a different configuration file as argument [-c configFile] to override the configuration in *meta4ics.conf*. If the file *meta4ics.conf* is not present, META4ICS uses the default configuration values (see below). 

### Solvers
* ```solvers.sat4j = true``` Enables/disables default MaxSAT solver (*default=true*)
* ```solvers.optim = false``` Enables/disables second Gurobi-based MaxSAT solver (*default=false*)

### Python environment
* ```python.path = /usr/local/bin/python3``` Specifies the path to the Python 3 binary (only used with the second [optional] Gurobi-based solver). 
* ```python.solver.path = python/optim.py``` Specifies the path to the Gurobi-based solver. 

### Output
* ```output.sol = true``` Indicates META4ICS to output the JSON solution with the critical nodes. 
* ```output.wcnf = false``` Enables/disables the specification of the problem in WCNF (DIMACS-like) format (*default=false*). The WCNF file can be used to experiment with other MaxSAT solvers. 
* ```output.txt = false``` Enables/disables the specification of the problem in a simple list-based representation file (*default=false*). 


### Output folders
* ```folders.output = output``` Specifies the default output folder for *.wcnf* and *.txt* files.
* ```folders.view = view``` Specifies the default view folder where the solution (*sol.json*) is stored. 

### Debug
* ```tool.debug = false``` Enables light debugging. 
* ```tool.fulldebug = false``` Enables full (heavy) debugging. 



