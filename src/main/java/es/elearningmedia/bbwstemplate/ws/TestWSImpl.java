package es.elearningmedia.bbwstemplate.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import blackboard.platform.ws.SessionVO;
import blackboard.platform.ws.VersionVO;
import blackboard.platform.ws.WebserviceContext;
import blackboard.platform.ws.anns.AuthenticatedMethod;
import blackboard.platform.ws.anns.FinalMethodDoNotChange;
import blackboard.platform.ws.anns.FinalWebServiceMethodsDoNotChange;
import blackboard.data.user.User;
import blackboard.persist.PersistenceException;
import blackboard.persist.user.UserDbLoader;

@WebService(name = "Test", serviceName = "Test", portName = "WS")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
@FinalWebServiceMethodsDoNotChange(methods = { "1:getUserName" }, releaseDates = { "1:Release 9.1 Feb. 2015" })
public class TestWSImpl implements TestWS
{  
  private static final long CURRENT_TEST_WS_VERSION = 1;
  private static final String THIS_WS_NAME = "Test";

  @WebMethod(action = "getServerVersion")
  @WebResult(name = "serverVersion")
  @FinalMethodDoNotChange(parameterTypes = { "blackboard.platform.ws.VersionVO" }, returnType = "blackboard.platform.ws.VersionVO", since = "1")
  public VersionVO getServerVersion( @WebParam(mode = WebParam.Mode.IN, name = "unused") VersionVO unused )
  {
    return new VersionVO( CURRENT_TEST_WS_VERSION );
  }

  @WebMethod(action = "initializeTestWS")
  @WebResult(name = "session")
  @FinalMethodDoNotChange(parameterTypes = { "boolean" }, returnType = "boolean", since = "1")
  public boolean initializeTestWS( boolean ignore )
  {
    SessionVO session = WebserviceContext.getCurrentSession();
    session.setClientWebServiceVersion( THIS_WS_NAME, 1 );
    return true;
  }
   
  @AuthenticatedMethod(entitlements={})
  @WebMethod(action = "getUserName")
  @FinalMethodDoNotChange(parameterTypes = { "java.lang.String" }, returnType = "java.lang.String", since = "1")
  public String getUserName( String uuid )
  {
	  String resultado = "";
	  
	  try {
		User user = UserDbLoader.Default.getInstance().loadByUuid(uuid);
		
		resultado = user.getUserName();
		
		} catch (PersistenceException e) {
			e.printStackTrace();
			resultado = e.getMessage();
		}
	  
	  return resultado;
  }
}
