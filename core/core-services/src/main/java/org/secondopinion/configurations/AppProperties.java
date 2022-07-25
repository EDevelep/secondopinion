package org.secondopinion.configurations;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties("app")
public class AppProperties {
    private Db db;
    private Smtp smtp;
    private Hibernate hibernate;
    private String notAuthorizedApis;
    private SSU ssu;
    private Fc fc;
    private Fcm fcm ;
   
	public Db getDb() {
        return db;
    }

    public void setDb(Db db) {
        this.db = db;
    }

    public Smtp getSmtp() {
        return smtp;
    }

    public void setSmtp(Smtp smtp) {
        this.smtp = smtp;
    }

    public Hibernate getHibernate() {
        return hibernate;
    }

    public void setHibernate(Hibernate hibernate) {
        this.hibernate = hibernate;
    }
    
    public Fcm getFcm() {
		return fcm;
	}

	public void setFcm(Fcm fcm) {
		this.fcm = fcm;
	}

	public Fc getFc() {
		return fc;
	}

	public void setFc(Fc fc) {
		this.fc = fc;
	}



	public static class Hibernate {
        private String dialect;
        private String show_sql;
        private String hbm2ddlAuto;

        public String getDialect() {
            return dialect;
        }

        public void setDialect(String dialect) {
            this.dialect = dialect;
        }

        public String getShow_sql() {
            return show_sql;
        }

        public void setShow_sql(String show_sql) {
            this.show_sql = show_sql;
        }
        
        public String getHbm2ddlAuto() {
        	return hbm2ddlAuto;
        }
        
        public void setHbm2ddlAuto(String hbm2ddlAuto) {
        	this.hbm2ddlAuto = hbm2ddlAuto;
        }

    }

    public static class Smtp {
        private String port;
        private String auth;
        private String starttlsEnable;
        private String user;
        private String password;
        private String host;
        private String errorMessageEmail;
        private String fromaddress;
        private String bounceBackAddress;

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
            this.port = port;
        }

        public String getAuth() {
            return auth;
        }

        public void setAuth(String auth) {
            this.auth = auth;
        }

        public String getStarttlsEnable() {
            return starttlsEnable;
        }

        public void setStarttlsEnable(String starttlsEnable) {
            this.starttlsEnable = starttlsEnable;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getFromaddress() {
            return fromaddress;
        }

        public void setFromaddress(String fromaddress) {
            this.fromaddress = fromaddress;
        }

        public String getBounceBackAddress() {
            return bounceBackAddress;
        }

        public void setBounceBackAddress(String bounceBackAddress) {
            this.bounceBackAddress = bounceBackAddress;
        }

		public String getErrorMessageEmail() {
			return errorMessageEmail;
		}

		public void setErrorMessageEmail(String errorMessageEmail) {
			this.errorMessageEmail = errorMessageEmail;
		}
    }

    public static class Db {

        private String url;
        private String userName;
        private String password;
        private String packagesToScan;
        private String driver;

        public String getUrl() { return url; }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPackagesToScan() {
            return packagesToScan;
        }

        public void setPackagesToScan(String packagesToScan) {
            this.packagesToScan = packagesToScan;
        }

		public final String getDriver() {
			return driver;
		}

		public final void setDriver(String driver) {
			this.driver = driver;
		}
        
        

    }
    
    public static class SSU {
    	private String baseUri;
    	private String createTokenUri;
    	private String userinfoUri;
    	private String regenerateTokenUri;
    	private String validateTokenUri;
    	private String removeTokenUri;
    	private String validateAndGenerateTokenUri;
    	
		public String getBaseUri() {
			return baseUri;
		}
		public void setBaseUri(String baseUri) {
			this.baseUri = baseUri;
		}
		public String getCreateTokenUri() {
			return getBaseUri()+createTokenUri;
		}
		public void setCreateTokenUri(String createTokenUri) {
			this.createTokenUri = createTokenUri;
		}
		public String getUserinfoUri() {
			return getBaseUri()+userinfoUri;
		}
		public void setUserinfoUri(String userinfoUri) {
			this.userinfoUri = userinfoUri;
		}
		public String getRegenerateTokenUri() {
			return getBaseUri()+regenerateTokenUri;
		}
		public void setRegenerateTokenUri(String regenerateTokenUri) {
			this.regenerateTokenUri = regenerateTokenUri;
		}
		public String getValidateTokenUri() {
			return getBaseUri()+validateTokenUri;
		}
		public void setValidateTokenUri(String validateTokenUri) {
			this.validateTokenUri = validateTokenUri;
		}
		public String getRemoveTokenUri() {
			return getBaseUri()+removeTokenUri;
		}
		public void setRemoveTokenUri(String removeTokenUri) {
			this.removeTokenUri = removeTokenUri;
		}
		public String getValidateAndGenerateTokenUri() {
			return getBaseUri()+validateAndGenerateTokenUri;
		}
		public void setValidateAndGenerateTokenUri(String validateAndGenerateTokenUri) {
			this.validateAndGenerateTokenUri = validateAndGenerateTokenUri;
		}
    	
    	
    	
    }

	public String getNotAuthorizedApis() {
		return notAuthorizedApis;
	}

	public void setNotAuthorizedApis(String notAuthorizedApis) {
		this.notAuthorizedApis = notAuthorizedApis;
	}

	public SSU getSsu() {
		return ssu;
	}

	public void setSsu(SSU ssu) {
		this.ssu = ssu;
	}
	
	public static class Fc {
		private String locationType;
		private String remoteServerLocation;
		private String host;
		private String accessKey;
		private String secretKey;
		private String bucketName;
		
		public String getLocationType() {
			return locationType;
		}
		public void setLocationType(String locationType) {
			this.locationType = locationType;
		}
		public String getRemoteServerLocation() {
			return remoteServerLocation;
		}
		public void setRemoteServerLocation(String remoteServerLocation) {
			this.remoteServerLocation = remoteServerLocation;
		}
		public String getHost() {
			return host;
		}
		public void setHost(String host) {
			this.host = host;
		}
		public String getAccessKey() {
			return accessKey;
		}
		public void setAccessKey(String accessKey) {
			this.accessKey = accessKey;
		}
		public String getSecretKey() {
			return secretKey;
		}
		public void setSecretKey(String secretKey) {
			this.secretKey = secretKey;
		}
		public String getBucketName() {
			return bucketName;
		}
		public void setBucketName(String bucketName) {
			this.bucketName = bucketName;
		}
		
		
	}
	
	public static class Fcm {

		private String firebaseConfigurationFile;
		
		public String getFirebaseConfigurationFile() {
			return firebaseConfigurationFile;
		}
		public void setFirebaseConfigurationFile(String firebaseConfigurationFile) {
			this.firebaseConfigurationFile = firebaseConfigurationFile;
		}
		
	}
}