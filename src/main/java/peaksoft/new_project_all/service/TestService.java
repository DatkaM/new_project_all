package peaksoft.new_project_all.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.new_project_all.dto.request.OptionRequest;
import peaksoft.new_project_all.dto.request.PassTestRequest;
import peaksoft.new_project_all.dto.request.QuestionRequest;
import peaksoft.new_project_all.dto.request.TestRequest;
import peaksoft.new_project_all.dto.response.*;
import peaksoft.new_project_all.model.*;
import peaksoft.new_project_all.repository.OptionRepository;
import peaksoft.new_project_all.repository.QuestionRepository;
import peaksoft.new_project_all.repository.StudentRepository;
import peaksoft.new_project_all.repository.TestRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;
    private final StudentRepository studentRepository;
    private final OptionRepository optionRepository;

    public TestResponse save(TestRequest request) {
        Test test = testRepository.save(convertToEntity(request));
        return new TestResponse(test.getId(), test.getName(), test.getDuration());
    }

    public TestInnerPageResponse getTestById(Long id) {

        return convertToResponse(testRepository.findById(id).orElseThrow(() ->
                new RuntimeException("not found")));
    }

    private TestInnerPageResponse convertToResponse(Test test) {
        TestInnerPageResponse testResponse = new TestInnerPageResponse(test.getId(), test.getName(), test.getDuration());
        List<QuestionResponse> questionResponseList = new ArrayList<>();
        for (Question q : test.getQuestions()) {
            QuestionResponse question = new QuestionResponse(q.getId(), q.getQuestion(), q.getQuestionType());

            List<OptionResponse> optionResponses = new ArrayList<>();
            for (Option o : q.getOptionList()) {
                OptionResponse option = new OptionResponse(o.getId(), o.getOption());
                optionResponses.add(option);
            }
            question.setOptions(optionResponses);
            questionResponseList.add(question);
        }
        testResponse.setQuestions(questionResponseList);
        return testResponse;
    }

    private Test convertToEntity(TestRequest request) {
        Test test = new Test(request.getName(), request.getDuration());
        for (QuestionRequest q : request.getQuestions()) {
            Question question = new Question(q.getQuestion(), q.getQuestionType());
            for (OptionRequest o : q.getOptions()) {
                Option option = new Option(o.getOption(), o.getIsTrue());
                question.addOptionToQuestion(option);
            }
            test.addQuestionToTest(question);
        }
        return test;
    }

    public ResultResponse passTest(PassTestRequest request, AuthInfo authInfo) {
        Integer countOfCorrectAnswer = 0;
        Test test = testRepository.findById(request.getTestId()).orElseThrow(() ->
                new RuntimeException("test not found"));
        for (Map.Entry<Long, List<Long>> a : request.getAnswer().entrySet()) {
//            Question question = questionRepository.findById(a.getKey()).get();
            for (Long optionId : a.getValue()) {
                if (optionRepository.findById(optionId).get().getIsTrue()) {
                    countOfCorrectAnswer++;
                }
            }
        }
        Student student = studentRepository.findByAuthInfoEmail(authInfo.getEmail());
        Integer wrongAnswer = test.getQuestions().size() + 1 - countOfCorrectAnswer;
        Result result = new Result(countOfCorrectAnswer,wrongAnswer,(countOfCorrectAnswer*5),student);
        return new ResultResponse(authInfo.getEmail(), countOfCorrectAnswer,
                test.getQuestions().size() + 1 - countOfCorrectAnswer,
                countOfCorrectAnswer);
    }
}
