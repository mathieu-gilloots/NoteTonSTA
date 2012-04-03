package com.supinfo.notetonsta.dao;

import java.util.List;

import com.supinfo.notetonsta.entity.Interventions;

public interface InterventionsDAO {

	Interventions addInterventions(Interventions newIntervention);
	Interventions updateIntervention(Interventions intervention);
	void removeIntervention(Interventions intervention);
	void removeIntervention(Long InterventionId);
	
	Interventions getInterventionById(Long id);
	
	Long getNumberEvaluation(Long InterventionId);
	Float getAVGSpeakerMarks(Long InterventionId);
	Float getAVGSlideMarks(Long InterventionId);
	Float getAVGAllMarks(Long InterventionId);
	
	String getStringChart(Long id);
	List<Float> getAVGAllMarksCombined(Long InterventionId);
	
}
