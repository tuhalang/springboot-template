package com.example.base.api.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
public class RoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        if (MasterConnectionAspect.isMasterConnection()) {
            log.info("Current dataSourceType: {}", RouterDbType.MASTER);
            return RouterDbType.MASTER;
        }

        boolean transactionActive = TransactionSynchronizationManager.isActualTransactionActive();

        if (transactionActive) {
            boolean readOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
            if (readOnly) {
                log.info("Current dataSourceType: {}", RouterDbType.SLAVE);
                return RouterDbType.SLAVE;
            }

            log.info("Current dataSourceType: {}", RouterDbType.MASTER);
            return RouterDbType.MASTER;
        }

        log.info("Current dataSourceType: {}", RouterDbType.SLAVE);
        return RouterDbType.SLAVE;
    }
}
