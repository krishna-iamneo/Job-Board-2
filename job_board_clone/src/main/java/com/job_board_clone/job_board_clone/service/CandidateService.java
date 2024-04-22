package com.job_board_clone.job_board_clone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.job_board_clone.job_board_clone.model.Candidate;
import com.job_board_clone.job_board_clone.model.Education;
import com.job_board_clone.job_board_clone.repository.CandidateRepository;
import com.job_board_clone.job_board_clone.repository.EducationRepository;

@Service
public class CandidateService {
    
    @Autowired
    CandidateRepository candidateRepository;
 
    @Autowired
    EducationRepository educationRepository;

    public Optional<Candidate> getCandidateById(long id) {
        return candidateRepository.findById(id);
    }

    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }
    
    public Candidate createCandidateProfile(Candidate candidate) {
        Candidate savedCandidate = candidateRepository.save(candidate);
        List<Education> educations = candidate.getEducations();
        for(int i = 0; i < educations.size(); i++) {
            educations.get(i).setCandidate(savedCandidate);
            educations.set(i, educationRepository.save(educations.get(i)));
        }
        savedCandidate.setEducations(educations);
        
        return candidateRepository.save(savedCandidate);
    }

    public Candidate updateCandidateProfile(long id, Candidate updatedCandidate) {
        // Check if the candidate exists
        Optional<Candidate> optionalCandidate = candidateRepository.findById(id);
        if (optionalCandidate.isPresent()) {
            Candidate candidate = optionalCandidate.get();
            // Update only non-null fields
            if (updatedCandidate.getName() != null) {
                candidate.setName(updatedCandidate.getName());
            }
            if (updatedCandidate.getDateOfBirth() != null) {
                candidate.setDateOfBirth(updatedCandidate.getDateOfBirth());
            }
            if (updatedCandidate.getAddress() != null) {
                candidate.setAddress(updatedCandidate.getAddress());
            }
            if (updatedCandidate.getContactDetails() != null) {
                candidate.setContactDetails(updatedCandidate.getContactDetails());
            }
            if (updatedCandidate.getGithubProfile() != null) {
                candidate.setGithubProfile(updatedCandidate.getGithubProfile());
            }
            if (updatedCandidate.getLinkedInProfile() != null) {
                candidate.setLinkedInProfile(updatedCandidate.getLinkedInProfile());
            }
            if (updatedCandidate.getPanNumber() != null) {
                candidate.setPanNumber(updatedCandidate.getPanNumber());
            }
            if (updatedCandidate.getAadharNumber() != null) {
                candidate.setAadharNumber(updatedCandidate.getAadharNumber());
            }
            if (updatedCandidate.getResumePath() != null) {
                candidate.setResumePath(updatedCandidate.getResumePath());
            }
            if (updatedCandidate.getPanCardPath() != null) {
                candidate.setPanCardPath(updatedCandidate.getPanCardPath());
            }
            if (updatedCandidate.getAadharCardPath() != null) {
                candidate.setAadharCardPath(updatedCandidate.getAadharCardPath());
            }    
            return candidateRepository.save(candidate);
        } else {
            return null;

        }
    }
    public Education updateEducation(long candidateId, long educationId, Education updatedEducation) {
        Optional<Candidate> optionalCandidate = candidateRepository.findById(candidateId);
        if (optionalCandidate.isPresent()) {
            Candidate candidate = optionalCandidate.get();
            List<Education> educations = candidate.getEducations();
            for (Education education : educations) {
                if (education.getId()==educationId) {
                    // Update only non-null fields
                    if (updatedEducation.getSchoolOrCollege() != null) {
                        education.setSchoolOrCollege(updatedEducation.getSchoolOrCollege());
                    }
                    if (updatedEducation.getUniversityOrBoard() != null) {
                        education.setUniversityOrBoard(updatedEducation.getUniversityOrBoard());
                    }
                    if (updatedEducation.getStreamOrStandard() != null) {
                        education.setStreamOrStandard(updatedEducation.getStreamOrStandard());
                    }
                    if (updatedEducation.getCgpa() != 0) {
                        education.setCgpa(updatedEducation.getCgpa());
                    }
                    if (updatedEducation.getYearOfPassing() != 0) {
                        education.setYearOfPassing(updatedEducation.getYearOfPassing());
                    }
                    // Update other fields as needed...
                    return educationRepository.save(education);
                }
            }
        }
        return null;
    }
      
}
