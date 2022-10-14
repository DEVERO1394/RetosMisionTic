package Service;


import Model.Client;
import Model.Cloud;
import Repository.CloudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CloudService {
    @Autowired
    private CloudRepository cloudRepository;
    public List<Cloud> getAll(){ return cloudRepository.getAll(); }

    public Optional<Cloud> getCloud(int  id){
        return  cloudRepository.getCloud(id);
    }

    public Cloud save (Cloud cloud){
        if(cloud.getId()==null){
            return cloudRepository.save((cloud));
        }else{
            Optional<Cloud> cloudEncontrada = cloudRepository.getCloud(cloud.getId());
            if(cloudEncontrada.isEmpty()){
                return cloudRepository.save(cloud);
            }else {
                return cloud;
            }
        }
    }
    public Cloud update (Cloud cloud) {
        if (cloud.getId() != null) {
            Optional<Cloud> cloudtEncontrado = getCloud(cloud.getId());
            if (!cloudtEncontrado.isEmpty()) {
                if (cloud.getName() != null) {
                    cloudtEncontrado.get().setName(cloud.getName());
                }
                if (cloud.getBrand() != null) {
                    cloudtEncontrado.get().setBrand(cloud.getBrand());
                }
                if (cloud.getYear() != null) {
                    cloudtEncontrado.get().setYear(cloud.getYear());
                }
                if (cloud.getDescription() != null) {
                    cloudtEncontrado.get().setDescription(cloud.getDescription());
                }
                if (cloud.getCategory() != null) {
                    cloudtEncontrado.get().setCategory(cloud.getCategory());
                }
                return cloudRepository.save(cloudtEncontrado.get());
            }
        }
        return cloud;
    }
        public boolean delete(int id){
            Boolean respuesta = getCloud(id).map(elemento ->{
                cloudRepository.delete(elemento);
                return true;
            }).orElse(false);

            return respuesta;
        }
}
