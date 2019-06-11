# META4ICS - Metric Analyser for Industrial Control Systems
### Version 0.53.1


## Requirements
* Java 8
* Python 2/3
* Optional: Python 3, PuLP, and Gurobi to enable second MaxSAT solver

## Usage

1. Compute metric: ```java -jar meta4ics.jar inputFile.json [-c configFile]```
This step executes META4ICS with an input JSON file that describes the network under analysis. 
The method used by META4ICS to compute the critical nodes is fully described in our paper: 
[Identifying Security-Critical Cyber-Physical Components in Industrial Control Systems](https://arxiv.org/abs/1905.04796)

2. Visualise solution graph: ```./web-viewer.py```
This command launches the webviewer (Python-based HTTP server) that displays the AND/OR graph with the critical nodes. 
By default, it starts at [http://localhost:8000/viz.html](http://localhost:8000/viz.html)

## Configuration
The configuration parameters are stored in the file ```meta4ics.conf```. 
### Output
* *output.sol = true* Indicates META4ICS to output the JSON solution with the critical nodes. 
* *output.sol = true* Indicates META4ICS to output the JSON solution with the critical nodes. 

### Output folders
folders.output = output
folders.view = view

## Execution example
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

## View the solution
```
$> ./web-viewer.py
Running in Python 2...
('Started HTTP server on port ', 8000)
```
Go to the browser and insert the viewer's URL: [http://localhost:8000/viz.html](http://localhost:8000/viz.html)

You should see the graph and the critical nodes:

![Screenshot - simple example](https://github.com/mbarrere/meta4ics/blob/master/screenshots/example1.png)
