/**
 * 
 */
package uk.ac.imperial.isst.metric;

import java.util.List;

import uk.ac.imperial.isst.metric.model.AndOrGraph;
import uk.ac.imperial.isst.metric.model.Measure;

/**
 * @author Martin Barrere <m.barrere@imperial.ac.uk>
 *
 */
public class ProblemSolution {

	private AndOrGraph graph;
	private List<Measure> measures;
	private SecurityMetric cut;
	
	
	public ProblemSolution(ProblemSpecification problem, SecurityMetric cut) {
		super();
		this.graph = problem.getGraph();
		this.cut = cut;
		this.measures = problem.getMeasures();
	}

	public AndOrGraph getGraph() {
		return graph;
	}

	public void setGraph(AndOrGraph graph) {
		this.graph = graph;
	}
	
	public List<Measure> getMeasures() {
		return measures;
	}

	public void setMeasures(List<Measure> measures) {
		this.measures = measures;
	}

	public SecurityMetric getCut() {
		return cut;
	}

	public void setCut(SecurityMetric cut) {
		this.cut = cut;
	}

}
