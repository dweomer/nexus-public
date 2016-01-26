/*
 * Sonatype Nexus (TM) Open Source Version
 * Copyright (c) 2008-present Sonatype, Inc.
 * All rights reserved. Includes the third-party code listed at http://links.sonatype.com/products/nexus/oss/attributions.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License Version 1.0,
 * which accompanies this distribution and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Sonatype Nexus (TM) Professional Version is available from Sonatype, Inc. "Sonatype" and "Sonatype Nexus" are trademarks
 * of Sonatype, Inc. Apache Maven is a trademark of the Apache Software Foundation. M2eclipse is a trademark of the
 * Eclipse Foundation. All other trademarks are the property of their respective owners.
 */
package org.sonatype.nexus.repository.selector;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.sonatype.nexus.formfields.ComboboxFormField;
import org.sonatype.nexus.formfields.StringTextFormField;
import org.sonatype.nexus.repository.selector.SelectorPreview.ContentType;
import org.sonatype.nexus.scheduling.TaskDescriptorSupport;

import static org.sonatype.nexus.formfields.FormField.MANDATORY;

/**
 * Selector preview task.
 *
 * @since 3.0
 */
@Named
@Singleton
public class SelectorPreviewTaskDescriptor
    extends TaskDescriptorSupport
{
  public static final String TYPE_ID = "repository.content-selector-preview";

  public static final String CONTENT_TYPE_FIELD_ID = "contentType";
  public static final String SELECTOR_FIELD_ID = "selectorExpression";

  @Inject
  public SelectorPreviewTaskDescriptor()
  {
    super(TYPE_ID,
        SelectorPreviewTask.class,
        "Selector preview",
        VISIBLE,
        NOT_EXPOSED,
        new ComboboxFormField<String>(
            CONTENT_TYPE_FIELD_ID,
            "Content to select",
            null,
            MANDATORY
        ).withStoreApi("coreui_Selector.readContentTypes")
         .withInitialValue(ContentType.component.name()),
        new StringTextFormField(
            SELECTOR_FIELD_ID,
            "Repository Selector Expression",
            "e.g. repository.format == 'maven2'",
            false
        )
    );
  }
}
