package br.com.asaplog.repository;

import br.com.asaplog.model.Customer;
import br.com.asaplog.model.InsurancePolicy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InsurancePolicyRepository extends MongoRepository<InsurancePolicy, String> {

    Optional<InsurancePolicy> findByPolicyNumber(String policyNumber);

}
