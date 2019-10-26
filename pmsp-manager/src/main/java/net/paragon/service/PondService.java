/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paragon.service;

import static com.github.adminfaces.template.util.Assert.has;

import java.io.Serializable;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.github.adminfaces.template.exception.BusinessException;

import net.paragon.entity.Car;
import net.paragon.entity.Pond;
import net.paragon.repository.PondRepository;

/**
 * @author rmpestano
 *         Pond Business logic
 */
@Component
public class PondService implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5145917994839866785L;

	@Inject
    private PondRepository pondRepository;

    public void insert(Pond pond) {
        validate(pond);
        pondRepository.save(pond);
    }

    public void carToPond(Car car) {
    	Pond pond = new Pond();
    	pond.setCode(car.getName());
    	pond.setArea(car.getPrice().floatValue());
    	pond.setLocation(car.getModel());
    	pondRepository.save(pond);
    }

    public void validate(Pond pond) {
        BusinessException be = new BusinessException();
        /*
        if (!pond.has()) {
            be.addException(new BusinessException("Pond model cannot be empty"));
        }
        if (!pond.hasLocation()) {
           be.addException(new BusinessException("Pond name cannot be empty"));
        }

        if (!has(pond.getPrice())) {
            be.addException(new BusinessException("Pond price cannot be empty"));
        }

        if (allPonds.stream().filter(c -> c.getName().equalsIgnoreCase(pond.getName())
                && c.getId() != c.getId()).count() > 0) {
            be.addException(new BusinessException("Pond name must be unique"));
        }
        */
        if(has(be.getExceptionList())) {
            throw be;
        }
    }
}
