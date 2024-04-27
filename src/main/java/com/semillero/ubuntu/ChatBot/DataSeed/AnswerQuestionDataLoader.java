package com.semillero.ubuntu.ChatBot.DataSeed;


import com.semillero.ubuntu.ChatBot.Entities.Answer;
import com.semillero.ubuntu.ChatBot.Entities.Question;
import com.semillero.ubuntu.ChatBot.Enums.QuestionType;
import com.semillero.ubuntu.ChatBot.Repositories.AnswerRepository;
import com.semillero.ubuntu.ChatBot.Repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnswerQuestionDataLoader implements CommandLineRunner {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    @Override
    public void run(String... args) throws Exception {
        loadAnswerQuestionData();
    }
    public void loadAnswerQuestionData() {
        if (answerRepository.count() == 0 && questionRepository.count()==0) {


           Question question1 = Question.createInitialQuestion("¿Quiénes somos?", QuestionType.INITIAL.name());
           Question question2 = Question.createInitialQuestion("¿En qué puedo invertir?", QuestionType.INITIAL.name());
           Question question3 = Question.createInitialQuestion("¿Cómo puedo contactar a los emprendedores?", QuestionType.INITIAL.name());
           Question question4 = Question.createInitialQuestion("¿Cómo sumar mi emprendimiento a Ubuntu?", QuestionType.INITIAL.name());

           Question question5 = Question.createSecondaryQuestion("Economía social/Desarrollo local/Inclusión financiera", QuestionType.SECONDARY.name());
           Question question6 = Question.createSecondaryQuestion("Agroecología/Orgánicos/Alimentación saludable", QuestionType.SECONDARY.name());
           Question question7 = Question.createSecondaryQuestion("Conservación/Regeneración/Servicios ecosistémicos", QuestionType.SECONDARY.name());
           Question question8 = Question.createSecondaryQuestion("Empresas/Organismos de impacto/Economía circular", QuestionType.SECONDARY.name());

            Answer answer1= Answer.createAnswer("Impulsamos el desarrollo de finanzas de impacto, liderando la transición hacia un modelo financiero sostenible.. Estamos emocionados de ayudarte a encontrar oportunidades de inversión que no solo generen ganancias, sino que también promuevan un impacto positivo en el medio ambiente y la sociedad.");
            Answer answer2= Answer.createAnswer("Ofrecemos oportunidades de inversión en economía social, desarrollo local, inclusión financiera y sostenibilidad ambiental. Desde agroecología hasta economía circular, nuestro enfoque abarca diversas áreas para generar impacto positivo y retorno financiero.");
            Answer answer3= Answer.createAnswer("Al ver el perfil de cada emprendedor, encontrarás un botón para CONECTAR con ellos.");
            Answer answer4= Answer.createAnswer("Mandanos un mail a ubuntusemillero@gmail.com con una descripción detallada de tu emprendimiento y fotos que resalten tu trabajo. Nos encantaría conocer más sobre lo que haces y cómo podemos colaborar juntos. ¡Esperamos ver tu información pronto!");
            Answer answer5= Answer.createAnswer("Promovemos el crecimiento económico equitativo, fortaleciendo comunidades y brindando acceso justo a servicios financieros");
            Answer answer6= Answer.createAnswer("Fomentamos la producción sostenible de alimentos, priorizando la salud del ecosistema y ofreciendo opciones nutritivas.");
            Answer answer7= Answer.createAnswer("Protegemos y restauramos los recursos naturales, generando beneficios para la biodiversidad y el bienestar humano.");
            Answer answer8= Answer.createAnswer("Impulsamos modelos de negocio responsables, innovadores y sostenibles que promueven la eficiencia de recursos y reducen residuos.");


            // Guardar respuestas
            answerRepository.save(answer1);
            answerRepository.save(answer2);
            answerRepository.save(answer3);
            answerRepository.save(answer4);
            answerRepository.save(answer5);
            answerRepository.save(answer6);
            answerRepository.save(answer7);
            answerRepository.save(answer8);

            // Guardar preguntas
            questionRepository.save(question1);
            questionRepository.save(question2);
            questionRepository.save(question3);
            questionRepository.save(question4);
            questionRepository.save(question5);
            questionRepository.save(question6);
            questionRepository.save(question7);
            questionRepository.save(question8);

            // Establecer relaciones entre preguntas y respuestas
            question1.addAnswer(answer1);
            question2.addAnswer(answer2);
            question3.addAnswer(answer3);
            question4.addAnswer(answer4);
            question5.addAnswer(answer5);
            question6.addAnswer(answer6);
            question7.addAnswer(answer7);
            question8.addAnswer(answer8);

            // Establecer preguntas secundarias para la respuesta correspondiente
            answer2.addSecondaryQuestion(question5);
            answer2.addSecondaryQuestion(question6);
            answer2.addSecondaryQuestion(question7);
            answer2.addSecondaryQuestion(question8);

            // Guardar nuevamente preguntas con relaciones actualizadas
            questionRepository.save(question1);
            questionRepository.save(question2);
            questionRepository.save(question3);
            questionRepository.save(question4);
            questionRepository.save(question5);
            questionRepository.save(question6);
            questionRepository.save(question7);
            questionRepository.save(question8);

            // Guardar nuevamente respuestas con relaciones actualizadas
            answerRepository.save(answer1);
            answerRepository.save(answer2);
            answerRepository.save(answer3);
            answerRepository.save(answer4);
            answerRepository.save(answer5);
            answerRepository.save(answer6);
            answerRepository.save(answer7);
            answerRepository.save(answer8);
        }
    }

}
