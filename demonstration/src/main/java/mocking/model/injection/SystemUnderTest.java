/**
 * 
 */
package mocking.model.injection;

import mocking.model.ToBeMocked;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.MoreObjects;

/**
 * @author Xavier Pigeon
 *
 */
@Component
public class SystemUnderTest extends mocking.model.SystemUnderTest {

    @Autowired
    private ToBeMocked dependency;

    @Autowired
    private ToBeMocked firstClassDependency;

    @Autowired
    private ToBeMocked secondClassDependency;

    @Autowired
    private ToBeMocked thirdClassDependency;

    @Autowired
    private mocking.model.legacy.HowItBehaves dependencyWithLegacyCode;

    public SystemUnderTest() {}

    public void setDependency(ToBeMocked thirdClassDependency) {
        this.dependency = thirdClassDependency;
    }

    public void setFirstClassDependency(ToBeMocked mock) {
        this.firstClassDependency = mock;
    }

    public void setSecondClassDependency(ToBeMocked secondClassDependency) {
        this.secondClassDependency = secondClassDependency;
    }

    public void setThirdClassDependency(ToBeMocked thirdClassDependency) {
        this.thirdClassDependency = thirdClassDependency;
    }

    public void setDependencyWithLegacyCode(mocking.model.legacy.HowItBehaves spy) {
        this.dependencyWithLegacyCode = spy;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this) //
                .add("dependency", dependency) //
                .add("firstClassDependency", firstClassDependency) //
                .add("secondClassDependency", secondClassDependency) //
                .add("thirdClassDependency", thirdClassDependency) //
                .add("dependencyWithLegacyCode", dependencyWithLegacyCode) //
                .omitNullValues() //
                .toString();
    }
}