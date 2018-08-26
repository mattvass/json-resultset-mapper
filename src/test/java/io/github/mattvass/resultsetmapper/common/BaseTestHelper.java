package io.github.mattvass.resultsetmapper.common;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

/** @author Matthew Vass Created: August 24, 2018 */
@RunWith(MockitoJUnitRunner.class)
public abstract class BaseTestHelper {

  @Before
  public void initMocks() {
    MockitoAnnotations.initMocks(this);
  }
}
