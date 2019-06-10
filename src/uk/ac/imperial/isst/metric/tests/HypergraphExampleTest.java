package uk.ac.imperial.isst.metric.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import uk.ac.imperial.isst.metric.CpsMetricAnalyser;
import uk.ac.imperial.isst.metric.ProblemSolution;
import uk.ac.imperial.isst.metric.ProblemSpecification;
import uk.ac.imperial.isst.metric.config.ToolConfig;
import uk.ac.imperial.isst.metric.util.JSONReader;

/**
 * @author Martin Barrere <m.barrere@imperial.ac.uk>
 *
 */

public class HypergraphExampleTest {
	private static ToolConfig config = null;
	
	@BeforeClass 
	public static void setUpClass() {		
		config = new ToolConfig(); 
		config.loadDefaultSetings();  
		config.tryLoadDefaultFromFile(CpsMetricAnalyser.CONFIG_FILE);
		//config.printProperties();				
		CpsMetricAnalyser.setupTool(config);	
		CpsMetricAnalyser.OUTPUT_SOL = false;
		CpsMetricAnalyser.OUTPUT_WCNF = false;
		CpsMetricAnalyser.OUTPUT_TXT = false;
		CpsMetricAnalyser.DEBUG = false;
		CpsMetricAnalyser.FULL_DEBUG = false;
		System.out.println("TEST class setup DONE!\n");
    }
	
	@AfterClass 
    public static void tearDownClass() {
		System.out.println("\nALL TESTS FINISHED");
    }
	
	@Before
    public void setUp() {
		System.out.println("TEST started");
		
	}
 
    @After
    public void tearDown() throws IOException {
    	System.out.println("TEST finished");
    }
    
    	
	@Test
	public void testICSSimple() {
		try {
			String filename = "examples/hypergraphs/ics-simple.json";					
			ProblemSpecification problem = new JSONReader().loadProblemSpecification(filename);		
			ProblemSolution sol = CpsMetricAnalyser.solveWithTseitinAndDisplaySolution(problem, config);
			
			assertEquals(Double.valueOf(6), sol.getCut().getCost());
			//sol.getCut().display();
		} catch (Exception e) {			
			e.printStackTrace();
			fail(e.getMessage());
		}		
	}
	
	
	@Test
	public void testICSExpanded() {
		try {
			String filename = "examples/hypergraphs/ics-expanded.json";
								
			ProblemSpecification problem = new JSONReader().loadProblemSpecification(filename);		
			ProblemSolution sol = CpsMetricAnalyser.solveWithTseitinAndDisplaySolution(problem, config);
			
			assertEquals(Double.valueOf(15), sol.getCut().getCost());
			//sol.getCut().display();
		} catch (Exception e) {			
			e.printStackTrace();
			fail(e.getMessage());
		}		
	}

}
