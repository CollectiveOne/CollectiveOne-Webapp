package org.collectiveone.modules.uprcl;

import java.sql.Timestamp;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.collectiveone.modules.uprcl.entities.Context;
import org.collectiveone.modules.uprcl.repositories.ContextRepositoryIf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContextService {
	
	private static final Logger logger = LogManager.getLogger(Service.class);
	
	@Autowired
	private ContextRepositoryIf contextRepository;
	
	@Transactional(rollbackOn = Exception.class)
	public Context getOrCreateUserContext(String did) {
		
		Context context = new Context();
		
		context.setCreator(did);
		context.setNonce(0L);
		context.setTimestamp(new Timestamp(0L));
		
		context.setId(context.computeId());
		context = contextRepository.save(context);
		
		return context;
	}

	
	

}