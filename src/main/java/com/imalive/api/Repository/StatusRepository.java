package com.imalive.api.Repository;

import com.imalive.api.Model.Base;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository  extends JpaRepository<Base, String> {
}
