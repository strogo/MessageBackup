package com.github.greyteardrop.messagebackup;

import com.beust.jcommander.JCommander;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Launcher {
	private final LaunchConfig launchConfig;
	private final EntityManagerFactory entityManagerFactory;
	private final EntityManager entityManager;

	private static final Logger logger = LoggerFactory.getLogger(Launcher.class);

	public Launcher(LaunchConfig launchConfig) {
		this.launchConfig = launchConfig;

		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("javax.persistence.jdbc.url",
			String.format("jdbc:h2:%s", launchConfig.getDbName()));
		entityManagerFactory = Persistence.createEntityManagerFactory(
			"com.github.greyteardrop.messagebackup", properties);
		entityManager = entityManagerFactory.createEntityManager();
	}

	public static void main(String[] args) {
		LaunchConfig config = new LaunchConfig();
		new JCommander(config, args);
		Launcher launcher = new Launcher(config);
		launcher.run();
	}

	private void run() {
		try {
			Config config = loadConfig();

			Server server = new Server(launchConfig.getPort());

			ServletContextHandler context = new ServletContextHandler();
			context.addServlet(AuthRequestServlet.class, "/");
			context.addServlet(TokenSaverServlet.class, "/return");
			server.setHandler(context);

			server.start();
			server.join();
		} catch (Exception e) {
			logger.error("Caught exception while running", e);
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
	}

	private Config loadConfig() {
		entityManager.getTransaction().begin();
		List<Config> configs = entityManager.createQuery("SELECT c FROM Config c", Config.class).getResultList();
		entityManager.getTransaction().commit();
		if (configs.isEmpty()) {
			Config config = new Config();
			entityManager.persist(config);
			return config;
		} else {
			return configs.get(0);
		}
	}
}
