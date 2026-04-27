"Our Herschel architecture was headless with 4 core systems:

**AEM** served as our content layer - component-based CMS handling page authoring, SEO-optimized static pages, image optimization, and publish workflows. Unlike SPA routing, AEM uses traditional links for better SEO.

**Spring Boot** was our data aggregation layer - REST APIs on Elastic Beanstalk (Dockerized, auto-scaled) that enriched AEM pages with dynamic data. Backend was RDS Postgres for cart/customer data. Async workflows used Lambda + SNS/SQS.

**Shopify** handled e-commerce - headless checkout, customer profiles, and inventory sync from **Bluecherry ERP** via webhooks that triggered AEM content updates.

Key decisions: Headless separated content velocity (AEM team) from commerce velocity (Shopify team). EBS gave us simple scaling for predictable Java traffic vs. Kubernetes complexity."