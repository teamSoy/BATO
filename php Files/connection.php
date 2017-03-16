<?php
	define('hostname','localhost');
	define('username','edc');
	define('password','test2016');
	define('databasename','teasoy');
	
	class DB_Connection
	{
		private $connect;
		function __construct(){
			$this->connect = mysqli	_connect(hostname,username,password,databasename) or die ("DB Connection Error");
		}
		public function get_connection()
		{
			return $this->connect;
		}
	}

?>