package com.comsysto.ui.testing;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends WebPage {

	public HomePage(final PageParameters parameters) {
		super(parameters);

		IModel<TimeSaving> timeSavingModel = Model.of(new TimeSaving(5,1000));

		setDefaultModel(timeSavingModel);

		final TextField<Integer> timePerUser = new TextField<>("timePerUser", new PropertyModel<>(getDefaultModel(), "timePerUser"));
		final TextField<Integer> amountUsers = new TextField<>("amountUsers", new PropertyModel<>(getDefaultModel(), "amountUsers"));
		final Label resultInSecondsPerDay = new Label("resultInSecondsPerDay", new PropertyModel<>(getDefaultModel(), "resultInSecondsPerDay"));
		final Label resultInSecondsPerYear = new Label("resultInSecondsPerYear", new PropertyModel<>(getDefaultModel(), "resultInSecondsPerYear"));
		final WebMarkupContainer results = new WebMarkupContainer("results");
		results.setVisible(false);
		add(results);

		Form form = new Form("form") {
			@Override
			protected void onSubmit() {
				timeSavingModel.getObject().calculateAndSetTimeSaving();
				results.setVisible(true);
			}
		};

		add(form);

		form.add(timePerUser);
		form.add(amountUsers);
		form.add(new SubmitLink("submit"));

		results.add(resultInSecondsPerDay);
		results.add(resultInSecondsPerYear);
    }
}
