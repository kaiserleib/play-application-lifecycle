package service;
import com.google.inject.ImplementedBy;
import service.impl.ServiceShutdownerImpl;

@ImplementedBy(ServiceShutdownerImpl.class)
public interface ServiceShutdowner {

}
