package br.com.usjt.partiu.web.util;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.NoArgsConstructor;
import lombok.Setter;

import org.joda.time.DateTime;

import br.com.caelum.vraptor.jasperreports.Report;

@NoArgsConstructor
public class ReportExporter<T> implements Report<T> {

	@Setter
	private String template;
	
	@Setter
	private String filename;
	
	@Setter
	private List<T> data;
	
	private Map<String, Object> parameters;
	
	public ReportExporter(String template, String filename, List<T> data){
		this.template = template;
		this.filename = filename;
		this.data = data;
		
		this.parameters = new HashMap<String, Object>();
	}
	
	@Override
	public Report<T> addParameter(String name, Object object) {
		this.parameters.put(name, object);
		
		return this;
	}

	@Override
	public Collection<T> getData() {
		return (Collection<T>) this.data;
	}

	@Override
	public String getFileName() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy-HHmmss");
		return this.filename + "-" + dateFormat.format(DateTime.now().toDate());
	}

	@Override
	public Map<String, Object> getParameters() {
		return this.parameters;
	}

	@Override
	public String getTemplate() {
		return "/" + template;
	}

}