package org.dllearner.algorithms.ParCEL;

/**
 * Refinement operator factory (RhoDRDown2008)
 * 
 * @author An C. Tran
 */
import java.util.List;
import java.util.Map;

import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.log4j.Logger;
import org.dllearner.algorithms.ParCEL.split.ParCELDoubleSplitterAbstract;
import org.dllearner.core.AbstractReasonerComponent;
import org.dllearner.core.owl.ClassHierarchy;
import org.dllearner.core.owl.DatatypeProperty;
import org.dllearner.core.owl.Description;
import org.dllearner.refinementoperators.RefinementOperator;
import org.dllearner.refinementoperators.RhoDRDown2008;

public class ParCELRefinementOperatorFactory extends BasePoolableObjectFactory<RefinementOperator> {
		
	private AbstractReasonerComponent reasoner;
	private ClassHierarchy classHierarchy;
	private Description startclass;
	private Map<DatatypeProperty, List<Double>> splits;
	private int maxNoOfSplits;
	

	private boolean useNegation = true;
	private boolean useDisjunction = true;
	private boolean useHasValue = false;
	
	
	Logger logger = Logger.getLogger(this.getClass());	
	
	public ParCELRefinementOperatorFactory(AbstractReasonerComponent reasoner, ClassHierarchy classHierarchy, Description startclass, int maxNoOfSplits) {
		this.reasoner = reasoner;
		this.classHierarchy = classHierarchy;
		this.startclass = startclass;
		this.splits = null;
		this.maxNoOfSplits = maxNoOfSplits;
	}
	
	
	public ParCELRefinementOperatorFactory(AbstractReasonerComponent reasoner, ClassHierarchy classHierarchy, 
			Description startclass, ParCELDoubleSplitterAbstract splitter) 
	{
		this.reasoner = reasoner;
		this.classHierarchy = classHierarchy;
		this.startclass = startclass;
		this.splits = splitter.computeSplits();
		
		if (logger.isDebugEnabled())
			logger.debug("Splits is calculated: " + splits);
	}
	
	
	public ParCELRefinementOperatorFactory(AbstractReasonerComponent reasoner, ClassHierarchy classHierarchy, 
			Description startclass, Map<DatatypeProperty, List<Double>> splits) 
	{
		this.reasoner = reasoner;
		this.classHierarchy = classHierarchy;
		this.startclass = startclass;
		this.splits = splits;	
	}
	
	@Override
	public RefinementOperator makeObject() throws Exception {
		
		//clone a new class heirarchy to avoid the competition between refinement operators
		ClassHierarchy clonedClassHierarchy = this.classHierarchy.clone();
		
		if (logger.isDebugEnabled())
			logger.info("A new refinement operator had been created");
		
		//create a new RhoDRDown and return
		RhoDRDown2008 refinementOperator = new RhoDRDown2008(this.reasoner, clonedClassHierarchy, this.startclass);
		
		refinementOperator.setAllowDisjunction(this.useDisjunction);
		refinementOperator.setUseNegation(this.useNegation);
		refinementOperator.setUseDataHasValueConstructor(this.useHasValue);
		refinementOperator.setUseHasValueConstructor(false);
		refinementOperator.setMaxNoOfSplits(maxNoOfSplits);
		
		if (this.splits != null)
			refinementOperator.setSplits(this.splits);
		
		
		//init the refinement operator;
		refinementOperator.init();
		
		return refinementOperator;
	}


	public boolean isUseNegation() {
		return useNegation;
	}


	public void setUseNegation(boolean useNegation) {
		this.useNegation = useNegation;
	}


	public boolean isUseDisjunction() {
		return useDisjunction;
	}


	public void setUseDisjunction(boolean useDisjunction) {
		this.useDisjunction = useDisjunction;
	}
	
	public void setUseHasValue(boolean useHasValue) {
		this.useHasValue = useHasValue;
	}
	
	public boolean getUseHasValue() {
		return this.useHasValue;
	}
	
}
