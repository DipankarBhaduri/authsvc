package com.mingleHub.authsvc.aspects;

import com.mingleHub.authsvc.constants.ErrorTitle;
import com.mingleHub.authsvc.exceptions.BaseException;
import com.mingleHub.authsvc.exceptions.DuplicateEmailException;
import com.mingleHub.authsvc.exceptions.IncorrectCredentialsException;
import com.mingleHub.authsvc.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class AuthControllerAdvice {

    @ExceptionHandler(
            {
                    DuplicateEmailException.class
            }
    )
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Problem onDuplicateEmailException (BaseException e) {
		return new Problem()
					   .setTitle(ErrorTitle.DUPLICATE_EMAIL.toString())
					   .setMessage(e.getLocalizedMessage());
    }

    @ExceptionHandler(
            {
					UserNotFoundException.class
            }
    )
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Problem onUserNotFoundExceptionError (BaseException e) {
		return new Problem()
					   .setTitle(ErrorTitle.USER_NOT_FOUND.toString())
					   .setMessage(e.getLocalizedMessage());
    }
	
	@ExceptionHandler(
			{
					IncorrectCredentialsException.class
			}
	)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public Problem onIncorrectCredentialsExceptionError (BaseException e) {
		return new Problem()
					   .setTitle(ErrorTitle.BAD_CREDENTIALS.toString())
					   .setMessage(e.getLocalizedMessage());
	}
}

/*
### Task Overview\n\nYou will be presented with a user prompt and two AI-generated responses, each accompanied by the corresponding code and API calls used in generating the responses. Your task is to interpret the user’s intent, consider any additional context provided, and rate each response from the user’s perspective.\n\nEvaluate the responses based on specific dimensions and provide an overall quality rating. Finally, compare the two responses and explain which one is superior and why.\n\n### </br> Evaluation Dimensions\n\n1. **Instruction Following**: Does the response adhere to the user’s prompt and instructions accurately?\n\n2. **Content Conciseness & Relevance**: Is the response focused, concise, and directly relevant to the user’s request?\n\n3. **Content Completeness**: Does the response provide a comprehensive answer covering all necessary aspects?\n\n4. **Writing Style & Tone**: Is the response clear, professional, and appropriately styled for the given context?\n\n5. **Collaborativity**: Does the response aim to collaborate with the user and provide actionable insights?\n\n6. **Contextual Awareness**: Is the response aware of and relevant to the broader conversation or task context?\n\n7. **Harmlessness**: Does the response avoid any harmful, misleading, or inappropriate content?\n\n8. **Truthfulness**: Is the information provided factually accurate and trustworthy?\n\n9. **Code Quality**: Does the code provided follow best practices, solve the problem effectively, and work as intended?\n\n### </br>Code Evaluation\n\nIn addition to assessing the natural language responses, you will also evaluate the code. When doing so, focus on the following four components:\n\n1. **Tools**: Are the correct tools being used for the task at hand?\n\n2. **Tool Methods**: Are the correct API methods invoked within the tools? Verify this by reviewing the code comments and outputs.\n\n3. **Parameters**: Are the correct parameters passed to the functions, and do they align with the user’s request? Do not focus on output verbosity or formatting in this context.\n\n4. **Processing/Reasoning Logic**: Does the code use appropriate logic to process the input and achieve the desired result?\n\n### </br>Available Tools\n\nHere is a list of tools that may appear in the responses. Consult the linked API documentation for more details about each tool’s capabilities, parameters, and available methods. Tools available may vary based on the model or platform (e.g., mobile or web):\n\n- **Youtube \uD83D\uDCF9**: Search for videos, get video metadata, and associated information [External] Youtube API v4\n\n- **Google Maps \uD83D\uDDFA️**: Perform location searches, retrieve directions, etc. [External] Google Maps API v8\n\n- **Google Search \uD83D\uDD0E**: Search the web for relevant results [External] Google Search API v0\n\n- **Google Flights ✈️**: Search for flights, compare prices, and get flight-related data [External] Google Flights API v9\n\n- **Google Hotels \uD83C\uDFE8**: Search for hotels and compare prices [External] Google Hotels API v8\n\n- **Browsing Tool \uD83D\uDCDA**: Fetch URL content and provide summarization [External] Browse API v0\n\n- **Workspace Tool ✏️**: Interact with Google Slides, Docs, Sheets, Gmail\n\n### </br>Notes\n\n- If a model completely refuses to answer the prompt or determines that using tools will not significantly improve the response over a default one, this should be considered a 'punt'.\n\n- Models may generate [Image] or [URL] tags as placeholders for images and URLs. These are placeholders that will be rendered later, so do not penalize the response for their presence.\n\n- Models may speak in the first person (using 'I'). This is intentional and should not be penalized, unless they are behaving like a person or giving personal opinions, which should only occur if asked to roleplay.
 */