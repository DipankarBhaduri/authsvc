//package com.mingleHub.authsvc.test;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//import lombok.Data;
//import lombok.experimental.Accessors;
//import java.util.List;
//import java.util.Map;
//
//@Data
//@Accessors(chain = true)
//public class ResumeDataResponseDTO {
//    private String _id;
//
//    @JsonProperty("gpt_resume_response")
//    private GPTResponse gptResumeResponse;
//    private String soulId;
//    private Map<String, Integer> token_usage;
//    private String created_at;
//
//    @Data
//    @Accessors(chain = true)
//    public static class GPTResponse {
//        @JsonProperty("name")
//        private String name;
//
//        @JsonProperty("email")
//        private String email;
//
//        @JsonProperty("undergrad_grad_year")
//        private int undergrad_grad_year;
//
//        @JsonProperty("masters_grad_year")
//        private int masters_grad_year;
//
//        @JsonProperty("postgrad_grad_year")
//        private String postgrad_grad_year;
//
//        @JsonProperty("undergrad_university")
//        private String undergrad_university;
//
//        @JsonProperty("masters_university")
//        private String masters_university;
//
//        @JsonProperty("postgrad_university")
//        private String postgrad_university;
//
//        @JsonProperty("currently_working?")
//        private boolean currently_working;
//
//        @JsonProperty("fte_exp_years")
//        private int fte_exp_years;
//
//        @JsonProperty("intern_exp_years")
//        private int intern_exp_years;
//
//        @JsonProperty("workex_industries")
//        private List<String> workex_industries;
//
//        @JsonProperty("workex_companies")
//        private List<String> workex_companies;
//
//        @JsonProperty("have_worked_in_startup?")
//        private boolean have_worked_in_startup;
//
//        @JsonProperty("fte_skills")
//        private List<String> fte_skills;
//
//        @JsonProperty("intern_skills")
//        private List<String> intern_skills;
//
//        @JsonProperty("project_skills")
//        private List<String> project_skills;
//
//        @JsonProperty("indian_language")
//        private String indian_language;
//
//        @JsonProperty("summary")
//        private String summary;
//    }
//
//    @Data
//    public static class TokenUsage {
//        private int completion_tokens;
//        private int prompt_tokens;
//        private int total_tokens;
//    }
//}
