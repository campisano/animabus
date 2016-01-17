package infrastructure;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import service.track.GpsBusService;

public class GetBusGPSPositionJob extends QuartzJobBean {

	private static final String APPLICATION_CONTEXT_KEY = "applicationContext";
	
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		ApplicationContext appCtx = getApplicationContext(context);
		GpsBusService gpsBusService = (GpsBusService) appCtx
				.getBean("gpsBusService");
		System.out.println("EXECUTING QUARTZ JOB");
		gpsBusService.insertListBus();
		System.out.println("QUARTZ JOB EXECUTED");
	}

	private ApplicationContext getApplicationContext(JobExecutionContext context)
			throws JobExecutionException {
		ApplicationContext appCtx = null;
		String error = new String();
		try {
			appCtx = (ApplicationContext) context.getScheduler().getContext()
					.get(APPLICATION_CONTEXT_KEY);
		} catch (Exception exc) {
			error = exc.toString();
		}
		if (appCtx == null) {
			throw new JobExecutionException(
					"No application context available in scheduler context for key \""
							+ APPLICATION_CONTEXT_KEY + "\""
							+ "Error details:\n" + error);
		}
		return appCtx;
	}
}