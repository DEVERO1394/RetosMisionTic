package Service;


import Model.Score;
import Repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreService {
    @Autowired
    private ScoreRepository scoreRepository;

    public List<Score> getAll(){
        return scoreRepository.getAll();
    }

    public Optional<Score> getScore(int  id){
        return  scoreRepository.getScore(id);
    }

    public Score save (Score score){
        if(score.getIdScore()==null){
            return scoreRepository.save((score));
        }else{
            Optional<Score> ScoreEncontrada = getScore(score.getIdScore());
            if(ScoreEncontrada.isEmpty()){
                return scoreRepository.save(score);
            }else {
                return score;
            }
        }
    }

    public Score update(Score score){
        if (score.getIdScore()!=null){
            Optional<Score> ScoreEncontrado = getScore(score.getIdScore());
            if (!ScoreEncontrado.isEmpty()){
                if (score.getStars()!=null){
                    ScoreEncontrado.get().setStars(score.getStars());
                }
                if (score.getMessageText()!=null){
                    ScoreEncontrado.get().setMessageText(score.getMessageText());
                }

                return scoreRepository.save(ScoreEncontrado.get());
            }
        }
        return score;
    }
    public boolean delete(int id){
        Boolean respuesta = getScore(id).map(elemento ->{
            scoreRepository.delete(elemento);
            return true;
        }).orElse(false);

        return respuesta;
    }
}
